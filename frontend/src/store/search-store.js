export const defaultSearchState = {
  currentPage: 0,
  totalPages: 0,
  blogs: [],
  query: "",
  hotKeywords: [],
};

export const searchStore = {
  namespaced: true,
  state: { ...defaultSearchState },
  mutations: {
    pageBlogs(state, pageBlogs = {}) {
      state.blogs = pageBlogs.content;
      state.currentPage = pageBlogs.number;
      state.totalPages = Math.min(50, pageBlogs.totalPages);
    },
    query(state, query) {
      state.query = query;
    },
    hotKeywords(state, hotKeywords) {
      state.hotKeywords = hotKeywords;
    },
  },
  getters: {
    blogs: (state) => state.blogs,
    currentPage: (state) => state.currentPage,
    totalPages: (state) => state.totalPages,
    query: (state) => state.query,
    hotKeywords: (state) => state.hotKeywords,
  },
  actions: {},
};
