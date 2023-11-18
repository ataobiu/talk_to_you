import { createStore } from 'vuex'

const store = createStore({
  state: {
    loginDialogVisible: false,
    cardDialogVisible: false,
  },
  getters: {
  },
  mutations: {
    changeLoginDialogVisible(state) {
      state.loginDialogVisible = !state.loginDialogVisible
    },
    changeCardDialogVisible(state) {
      state.cardDialogVisible = !state.cardDialogVisible
    }
  },
  actions: {
  },
  modules: {
  }
})

export default store;
