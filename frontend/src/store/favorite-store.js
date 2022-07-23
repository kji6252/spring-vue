export const defaultFavoriteState = {
  currentPage: 0,
  totalPages: 0,
  blogs: [],
};

export const favoriteStore = {
  namespaced: true,
  state: { ...defaultFavoriteState },
  mutations: {
    pageBlogs(state, pageBlogs = {}) {
      state.blogs = pageBlogs.content;
      state.currentPage = pageBlogs.number;
      state.totalPages = pageBlogs.totalPages;
    },
    removeBlog(state, index) {
      state.blogs.splice(index, 1);
    },
  },
  getters: {
    blogs: (state) => state.blogs,
    currentPage: (state) => state.currentPage,
    totalPages: (state) => state.totalPages,
  },
  actions: {},
};
