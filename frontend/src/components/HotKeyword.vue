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
import axios from "axios";
export default {
  name: "HotKeyword",
  data() {
    return {
      hotKeywords: [],
    };
  },
  methods: {
    fetchHotKeywords() {
      axios
        .get("/api/hot-keywords")
        .then((response) => (this.hotKeywords = response.data));
    },
    intervalFetchData() {
      setInterval(() => {
        this.fetchHotKeywords();
      }, 1000);
    },
  },
  mounted() {
    this.fetchHotKeywords();
    this.intervalFetchData();
  },
};
</script>

<style scoped></style>
