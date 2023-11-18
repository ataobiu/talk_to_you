<template>
  <div class="main">
    <router-view />
  </div>
</template>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}

html,
body {
  margin: 0;
  padding: 0;
  font-family: 'Courier New', Courier, monospace;
  font-size: 20px;
  max-height: 100vh;
  max-width: 100vw;
  box-sizing: border-box;

  ul,
  li {
    list-style: none;
  }

  a {
    text-decoration: none;
    color: inherit;
    outline: none;
  }
}

.main {
  max-width: 100rem;
  margin: 0 auto;
}
</style>
