// 스토어는 여러 컴포넌트가 공유하는 반응형 상태를 중앙에서 관리하도록 하는 기술, 반응형 상태를 일관되고 효율적으로 관리하기 위함이다.
// 대표적인 기술로 Pinia,Vuex가 있으며 최신 기술은 Pinia로 가볍고 간결한 문법을 제공한다
// vue 개발팀은 새로운 앱은 Vuex보다 Pinia를 사용할것을 권장하고 있다.

import {defineStore} from 'pinia'

export const useAccountStore = defineStore("account", {
    state: () => ({
        checked: false,
        loggedIn: false,
        accessToken: "", // 엑세스 토큰 프로퍼티, 데이터 타입은 문자열
    }),
    actions: {
        setChecked(val) {
            this.checked = val;
        },
        setLoggedIn(val) {
            this.loggedIn = val;
        },
        setAccessToken(val) { // ②
            this.accessToken = val;
        },
    },
});