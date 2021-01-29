import Vue from 'vue'
import Router from 'vue-router'
import Cargo from './views/Cargo.vue'
import Usuarios from './views/Usuarios.vue'
import VueMaterial from 'vue-material'
import 'vue-material/dist/vue-material.min.css'
import 'vue-material/dist/theme/default.css'
import VueModal from '@kouts/vue-modal';
import '@kouts/vue-modal/dist/vue-modal.css';

Vue.component('Modal', VueModal);
Vue.use(VueMaterial)
Vue.use(Router)

export default new Router ({
    mode:'history',
    routes: [
        {
            path: "/cargos",
            name: "cargos",
            component: Cargo
        },
        {
            path: "/usuarios",
            name: "usuarios",
            component: Usuarios
        },
    ]
})