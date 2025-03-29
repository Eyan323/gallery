<script setup>
import {getItems} from "@/services/itemService";
import {reactive} from "vue";
import Card from "@/components/Card.vue";

// 반응형 상태
const state = reactive({
  items: []
});

// 커스텀 생성 훅
(async function onCreated() {
  const res = await getItems();

  if (res.status === 200) {
    state.items = res.data;
    // 상품 서비스 상품 목록 조회 getItems 메서드를 호출, 리턴 데이터를 items에 입력
  }
})();
</script>

<template>
  <div class="home">
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-3">
          <div class="col" v-for="item in state.items">
            <!-- v-for 디랙티브를 이용해 상품 데이터의 개수만큼 반복해서 출력 -->
            <Card :item="item"/>
            <!-- item 속성에 각 상품 데이터를 입력해 전달 -->
          </div>
        </div>
      </div>
    </div>
  </div>
</template>