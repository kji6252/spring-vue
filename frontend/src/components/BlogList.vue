<template>
  <ul v-if="blogs.length > 0">
    <li v-for="(blog, index) in blogs" :key="index">
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
    </li>
  </ul>
</template>

<script>
export default {
  name: "BlogList",
  props: {
    blogs: [],
  },
};
</script>

<style scoped></style>
