<script setup>
import {addItem} from "@/services/cartService";
import {useRouter} from "vue-router";
import {computed} from "vue";
import {useAccountStore} from "@/stores/account";

// 컴포넌트는 화면의 영역을 구분하여 개발할 수 있는 뷰의 기능이다.
// 컴포넌트 기반으로 화면을 개발하게 되면 코드의 재사용성이 올라가고 빠르게 화면을 제작할 수 있어서 아주 유용함.

const props = defineProps({
  item: {
    id: Number,
    imgPath: String,
    name: String,
    price: Number,
    discountPer: Number
  }
});

// 상품 할인가
const computedItemDiscountPrice = computed(() => {
  return (props.item.price - (props.item.price * props.item.discountPer / 100)).toLocaleString() + '원';
})

// 라우터 객체
const router = useRouter();

// 계정 스토어
const accountStore = useAccountStore();

// 장바구니에 상품 담기
const put = async () => {
  if (!accountStore.loggedIn) {
    if (window.confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")) {
      await router.push("/login");
    }

    return;
  }

  const res = await addItem(props.item.id);

  if (res.status === 200 && window.confirm('장바구니에 상품을 담았습니다. 장바구니로 이동하시겠습니까?')) {
    await router.push("/cart");
    // 장바구니에 상품 추가시 페이지 이동 여부 확인 창을 표시, 확인버튼 클릭후 장바구니 페이지로 이동
  }
};
</script>

<template>
  <div class="card shadow-sm">
    <!-- 상품 사진 출력 -->
    <span class="img" :style="{backgroundImage: `url(${props.item.imgPath})`}"
          :aria-label="`상품 사진(${props.item.name})`"></span>
    <div class="card-body">
      <p class="card-text">
        <!-- 상품 이름 -->
        <span class="me-2">{{ props.item.name }}</span>
        <!-- 상품 할인율 -->
        <span class="discount badge bg-danger">{{ props.item.discountPer }}%</span>
      </p>
      <div class="d-flex justify-content-between align-items-center">
        <button class="btn btn-primary btn-sm" @click="put()">장바구니 담기</button>
        <!-- 상품 정가(숫자 데이터에 3자리마다 쉼표 표기) -->
        <small class="price text-muted">{{ props.item.price.toLocaleString() }}원</small>
        <!-- 상품 할인가 -->
        <small class="real text-danger">{{ computedItemDiscountPrice }}</small>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.card {
  .img {
    display: inline-block;
    width: 100%;
    height: 250px;
    background-size: cover;
    background-position: center;
  }

  .card-body .price {
    text-decoration: line-through;
  }
}
</style>