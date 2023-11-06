import { createStore } from 'vuex'

const store = createStore({
  state: {
    loginDialogVisible: false,
    userInfo: null
  },
  getters: {
  },
  mutations: {
    changeLoginDialogVisible(state) {
      state.loginDialogVisible = !state.loginDialogVisible
    },
    setUserInfo(state, value) {
      state.userInfo = value;
    },
    watchLocalStorage(state) {
      const storedValue = localStorage.getItem('userInfo');
      if (storedValue !== null) {
        state.userInfo = JSON.parse(storedValue);
      }
      // 监听 localStorage 变化
      window.addEventListener('storage', (event) => {
        if (event.key === 'userInfo' && event.newValue !== null) {
          state.userInfo = JSON.parse(event.newValue);
          console.log(state.userInfo);
        }
      });
    }
  },
  actions: {
  },
  modules: {
  }
})

export default store;
