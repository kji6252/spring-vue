<template>
  <div class="hello">
    <h1>블로그 검색</h1>
    <div>
      <span><search-form :query="query" /></span>
      <span><hot-keyword /></span>
    </div>

    <blog-list :blogs="blogs" />
    <page-navigator
      :query="query"
      :current-page="currentPage"
      :total-pages="totalPages"
    />
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import SearchForm from "@/components/SearchForm";
import BlogList from "@/components/BlogList";
import PageNavigator from "@/components/PageNavigator";
import HotKeyword from "@/components/HotKeyword";
export default {
  name: "SearchView",
  components: { HotKeyword, PageNavigator, BlogList, SearchForm },
  computed: {
    ...mapGetters("searchStore", [
      "blogs",
      "query",
      "currentPage",
      "totalPages",
    ]),
  },
};
</script>
