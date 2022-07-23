<template>
  <div class="about">
    <h1>즐겨 찾은 블로그들</h1>
    <blog-list :blogs="blogs" button-text="삭제" :button-event="removeBlog" />
    <page-navigator
      :current-page="currentPage"
      :start-page="0"
      :total-pages="totalPages"
      :page-forward-event="doFavoriteBlogs"
    />
  </div>
</template>
<script>
import axios from "axios";
import { mapGetters } from "vuex";
import BlogList from "@/components/BlogList";
import PageNavigator from "@/components/PageNavigator";
export default {
  name: "FavoriteBlogs",
  components: { PageNavigator, BlogList },
  computed: {
    ...mapGetters("favoriteStore", ["blogs", "currentPage", "totalPages"]),
  },
  created() {
    this.doFavoriteBlogs(0);
  },
  methods: {
    removeBlog(blog, index) {
      axios
        .delete("/api/favorite-blogs/" + blog.blogHashCode)
        .then((response) => {
          console.log(response);
          this.$store.commit("favoriteStore/removeBlog", index);
        })
        .catch((err) => console.error(err));
    },
    doFavoriteBlogs(pageNumber) {
      axios
        .get("/api/favorite-blogs?page=" + pageNumber)
        .then((response) => {
          this.$store.commit("favoriteStore/pageBlogs", response.data);
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
};
</script>
