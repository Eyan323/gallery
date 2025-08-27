<script setup>
import {useAccountStore} from "@/stores/account";
import {logout} from "@/services/accountService";
import {useRouter} from "vue-router";

// 계정 스토어
const accountStore = useAccountStore();

// 라우터 객체
const router = useRouter();

// 로그아웃
const logoutAccount = async () => {
  const res = await logout();

  if (res.status === 200) {
    accountStore.setAccessToken("");
    // 로그아웃 성공시 계정 스토어 엑세스 토큰 값을 초기화하여 이후 HTTP 요청에서 토큰이 사용되지 않게함.
    accountStore.setLoggedIn(false);
    await router.push("/");
  }
};
</script>

<template>
  <!--뷰 템플릿이란 HTML, CSS 등의 마크업 속성과 뷰 인스턴스에서 정의한 데이터 및 로직들을 연결하여 사용자가 브라우저에서 볼 수 있는 형태의 HTML로 변환해주는 속성 -->
  <!--간단하게 뷰에서 화면을 담당하는 기술로 여기에 변수를 삽입하여 각자 다른 페이지를 보여주는 기술-->
  <header>
    <div class="navbar navbar-dark bg-dark text-white shadow-sm">
      <div class="container">
        <router-link to="/" class="navbar-brand">
          <strong>일본 소도시 여행 상품</strong>
          <!--메인으로 이동하는 경로의 라우터 링크 요소-->
        </router-link>
        <div class="menus d-flex gap-3">
          <template v-if="!accountStore.loggedIn">
            <!-- 사용자가 로그인 하지 않은 상태라면-->
            <router-link to="/login">로그인</router-link>
            <router-link to="/join">회원가입</router-link>
          </template>
          <template v-else>
            <!--사용자가 로그인 한 상태라면-->
            <a @click="logoutAccount()">로그아웃</a>
            <router-link to="/orders">주문 내역</router-link>
            <router-link to="/cart">장바구니</router-link>
          </template>
        </div>
      </div>
    </div>
  </header>
</template>

<style lang="scss">
header {
  .menus {
    a {
      cursor: pointer;
      color: #fff;
      text-decoration: none;
    }
  }
}
</style>