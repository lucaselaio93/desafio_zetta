<template>
  <div id="app">
    <div class="page-container">
      <md-app md-waterfall md-mode="fixed">
        <md-app-toolbar class="md-primary">
          <span class="md-title"></span>
        </md-app-toolbar>

        <md-app-drawer md-permanent="full">
          <md-toolbar class="md-transparent md-layout md-alignment-center-center" md-elevation="0">
            <img src="./assets/logo-zetta.png" alt="">
          </md-toolbar>

          <md-list>
            <md-list-item md-elevation="5">
              <md-icon>work</md-icon>
              <router-link to="/cargos" class="md-list-item-text">Cargos</router-link>
            </md-list-item>
            <md-list-item md-elevation="5">
              <md-icon>contact_page</md-icon>
              <router-link to="/usuarios" class="md-list-item-text">Usuarios</router-link>
            </md-list-item>
          </md-list>
        </md-app-drawer>

        <md-app-content>
          <router-view></router-view>
        </md-app-content>
      </md-app>
    </div>
  </div>
</template>



<script>
import NavBar from './components/navBar';
export default {
  name: 'App',
  components: {
    NavBar
  }
}
</script>

<style>

</style>
