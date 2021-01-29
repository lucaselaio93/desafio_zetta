<template>
  <div>
    <md-tabs md-sync-route>
      <md-tab id="tab-cargos" md-label="Cargos" to="/cargos" exact></md-tab>
      <md-tab id="tab-usuarios" md-label="Usuarios" to="/usuarios" exact></md-tab>
    </md-tabs>
  </div>
</template>

<script>
  export default {
    name: 'navBar'
  }
</script>

<style lang="scss" scoped>
  .md-tabs {
    margin-bottom: 24px;
  }
</style>