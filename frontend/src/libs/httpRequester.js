import axios from "axios";
import {useAccountStore} from "@/stores/account";

// JavaScript는 개발자가 대화식 웹 페이지를 만들기 위해 사용하는 프로그래밍 언어.
// 정적 페이지는 주로 고정된 레이아웃으로 정보를 표시했으며, 현대 웹 사이트에서 기대하는 기능을 모두 수행하지는 못함.
// JavaScript는 웹 애플리케이션을 보다 동적으로 만드는 브라우저 측 기술로 등장. 이는 매우 중요한 역할이다.

const instance = axios.create();

// 인터셉터(응답 시)
instance.interceptors.response.use((res) => {
    return res;
}, async (err) => {
    switch (err.response.status) {
        case 400:
            window.alert("잘못된 요청입니다.");
            break;

        case 401:
            const config = err.config;

            if (config.retried) { // 재요청 여부
                window.alert("권한이 없습니다.");
                window.location.replace("/");
                return;
            }

            // (쿠키에 있는) 리프레시 토큰으로 액세스 토큰 요청
            const res = await axios.get("/v1/api/account/token");

            // 액세스 토큰
            const accessToken = res.data;

            // 계정 스토어
            const accountStore = useAccountStore();

            // 계정 스토어의 액세스 토큰 변경
            accountStore.setAccessToken(accessToken);

            // 요청 액세스 토큰 교체
            config.headers.authorization = `Bearer ${accountStore.accessToken}`;

            // 중복 재요청 방지를 위한 프로퍼티 추가
            config.retried = true;

            // 재요청
            return instance(config);

        case 500:
            window.alert("오류가 있습니다. 관리자에게 문의해주세요.");
            break;
    }

    return Promise.reject(err);
});

// HTTP 요청 설정 생성
const generateConfig = () => {
    // 계정 스토어
    const accountStore = useAccountStore();

    if (accountStore.accessToken) {
        return {
            headers: {authorization: `Bearer ${accountStore.accessToken}`}
        };
    }

    return {};
};

export default {
    get(url, params) {
        const config = generateConfig();
        config.params = params;
        return instance.get(url, config);
    },
    post(url, params) {
        return instance.post(url, params, generateConfig());
    },
    put(url, params) {
        return instance.put(url, params, generateConfig());
    },
    delete(url) {
        return instance.delete(url, generateConfig());
    },
};