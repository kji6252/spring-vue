export const defaultAccountState = {
  userIdentity: null,
  authenticated: false,
};

export const accountStore = {
  namespaced: true,
  state: { ...defaultAccountState },
  mutations: {
    authenticated(state, identity) {
      state.userIdentity = identity;
      state.authenticated = true;
    },
    logout(state) {
      state.userIdentity = null;
      state.authenticated = false;
    },
  },
  getters: {
    authenticated: (state) => state.authenticated,
  },
};
