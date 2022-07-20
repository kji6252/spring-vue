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
  },
  actions: {
    account: (state) => state.userIdentity,
    authenticated: (state) => state.authenticated,
  },
};
