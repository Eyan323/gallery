<script setup>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import {useAccountStore} from "@/stores/account";
import {watch} from "vue";
import {useRoute} from "vue-router";
import {check} from "@/services/accountService";

// vue 는 뛰어난 성능, 직관적 문법, 낮은 진압장벽으로 가장 인기있는 프론트엔드 프레임워크중 하나이다.
// 특히 vue3 버전은 이전 버전보다 비약적인 성능향상을 보여줌. 번들 크기 감소, 렌더링 속도 향상, 메모리 사용량 감소에서 대폭적인 성능업

// 계정 스토어
const accountStore = useAccountStore();

// 라우트 객체
const route = useRoute();

// 로그인 여부 확인
const checkAccount = async () => {
  const res = await check();

  if (res.status === 200) {
    accountStore.setChecked(true);
    accountStore.setLoggedIn(res.data === true);
  } else {
    accountStore.setChecked(false);
  }
};

// 커스텀 생성 훅
(async function onCreated() {
  await checkAccount();
  // 컴포넌트 라이프사이클 훅 중 가장 먼저 실행되는 생성 훅
  // async는 비동기 함수의 선언, await는 Promise가 처리될때 까지 기다리는것.
  // 즉 Promise 기반 비동기 코드를 동기처럼 보이고 처리 가능하게 할때 작성하는 코드.

})();

// 라우트 경로가 바뀔 때마다 로그인 여부를 확인
watch(() => route.path, () => {
  checkAccount();
  // watch: 특정 값이 변할 때 실행되는 감시자. route.path 변경시 로그인 여부 확인을 위한 checkAccount 메서드를 호출
});
</script>

<template>
  <!-- 로그인 체크 여부 확인 후 출력-->
  <template v-if="accountStore.checked">
    <Header/>
    <main>
      <!-- 라우터 뷰 -->
      <router-view></router-view>
      <!-- 라우터 뷰: URL 경로에 따라 동적으로 콘텐츠가 바뀔 수 있는 요소-->
    </main>
    <Footer/>
  </template>
</template>