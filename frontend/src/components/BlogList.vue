<template>
  <ul v-if="blogs.length > 0">
    <li v-for="(blog, index) in blogs" :key="index">
      <div :ref="'div' + index">
        <button v-if="authenticated" @click="buttonEvent(blog, index)">
          {{ buttonText }}
        </button>
        <a :href="blog.url" target="_blank">
          <p><span>제목 : </span><span v-html="blog.title"></span></p>
          <p><span>내용 : </span><span v-html="blog.description"></span></p>
          <p>
            <span>이름 : </span><span>{{ blog.blogName }}</span>
          </p>
          <p>
            <span>작성일 : </span><span>{{ blog.createdDate }}</span>
          </p>
        </a>
      </div>
    </li>
  </ul>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "BlogList",
  props: {
    blogs: [],
    buttonText: String,
    buttonEvent: Function,
  },
  computed: {
    ...mapActions("accountStore", ["authenticated"]),
  },
};
</script>

<style scoped></style>
