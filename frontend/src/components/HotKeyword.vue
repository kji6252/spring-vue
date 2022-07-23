<template>
  <div>
    <h3>인기 검색어</h3>
    <ul>
      <li v-for="(hotKeyword, index) in hotKeywords" :key="index">
        <span>{{ index + 1 }}</span>
        <span>{{ hotKeyword.keyword }}</span>
        <span>{{ hotKeyword.searchCount }}</span>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "HotKeyword",
  computed: {
    ...mapGetters("searchStore", ["hotKeywords"]),
  },
};
</script>

<style scoped></style>
