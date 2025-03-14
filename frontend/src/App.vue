<script setup>
import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import {useAccountStore} from "@/stores/account";
import {watch} from "vue";
import {useRoute} from "vue-router";
import {check} from "@/services/accountService"; // ①

// vue 는 뛰어난 성능, 직관적 문법, 낮은 진압장벽으로 가장 인기있는 프론트엔드 프레임워크중 하나이다.
// 특히 vue3 버전은 이전 버전보다 비약적인 성능향이 있었다. 번들 크기 감소, 렌더링 속도 향상, 메모리 사용량 감소에서 대폭적인 성능업이 있었다.

// 계정 스토어
const accountStore = useAccountStore();

// 라우트 객체
const route = useRoute();

// 로그인 여부 확인
const checkAccount = async () => { // ②
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
})();

// 라우트 경로가 바뀔 때마다 로그인 여부를 확인
watch(() => route.path, () => {
  checkAccount();
});
</script>

<template>
  <!-- 로그인 체크 여부 확인 후 출력-->
  <template v-if="accountStore.checked"> <!-- ③ -->
    <Header/>
    <main>
      <!-- 라우터 뷰 -->
      <router-view></router-view>
    </main>
    <Footer/>
  </template>
</template>