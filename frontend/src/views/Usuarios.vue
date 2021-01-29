<template>
  <div>
    <div>
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          <b class="field">Erro</b>: {{ errors.error }}
        </li>
      </ul>
      <Modal v-model="showModal" title="Gerenciar Usuário">
        <form @submit.prevent="salvar">
          <md-input type="hidden" v-model="usuario.id"></md-input>
          <md-card-content>
            <div class="md-layout md-gutter">
              <div class="md-layout-item md-small-size-100">
                <md-field>
                  <label>Nome Completo</label>
                  <md-input v-model="usuario.nome" />
                </md-field>
              </div>

              <div class="md-layout-item md-small-size-100">
                <md-field>
                  <label>CPF</label>
                  <md-input v-model="usuario.cpf" />
                </md-field>
              </div>
            </div>
            <div class="md-layout md-gutter">
              <div class="md-layout-item md-small-size-100">
                <md-field>
                  <md-input
                    @change="formataData(dataNaoFormatada)"
                    type="date"
                    v-model="dataNaoFormatada"
                  ></md-input>
                  <span class="md-helper-text">Data de nascimento</span>
                </md-field>
              </div>
            </div>
            <div class="md-layout md-gutter">
              <div class="md-layout-item">
                <md-field>
                  <label>Cargo</label>
                  <md-select @md-selected="buscarCargo(usuario.cargo)" v-model="usuario.cargo">
                    <md-option
                      v-for="cargo of cargos"
                      :key="cargo.id"
                      :value="cargo.id"
                      >{{ cargo.nome }}</md-option
                    >
                  </md-select>
                </md-field>
              </div>
            </div>
          </md-card-content>
          <md-card-actions>
            <md-button type="submit" class="md-primary">Salvar</md-button>
          </md-card-actions>
        </form>
      </Modal>
      <md-button type="submit" class="md-primary md-raised" @click="abreModal(true)"
        >Cadastrar Usuário</md-button
      >
    </div>
    <md-table v-model="usuarios" md-sort="name" md-sort-order="asc" md-card>
      <md-table-toolbar>
        <h1 class="md-title">Usuários</h1>
      </md-table-toolbar>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="Nome" md-sort-by="nome">{{ item.nome }}</md-table-cell>
        <md-table-cell md-label="CPF" md-sort-by="cpf">{{ item.cpf }}</md-table-cell>
        <md-table-cell md-label="Data de Nascimento" md-sort-by="dataNascimento">{{
          item.dataNascimento
        }}</md-table-cell>
        <md-table-cell md-label="Data do Cadastro" md-sort-by="dataCadastro">{{
          item.dataCadastro
        }}</md-table-cell>
        <md-table-cell md-label="Cargo" md-sort-by="cargo" v-if="item.cargo">{{
          item.cargo.nome
        }}</md-table-cell>
        <md-table-cell md-label="Cargo" md-sort-by="cargo" v-else>{{ item.cargo }}</md-table-cell>
        <md-table-cell md-label="">
          <md-button @click="editar(item)" class="md-icon-button md-raised md-primary">
            <md-icon>edit</md-icon>
          </md-button>
        </md-table-cell>
        <md-table-cell md-label="">
          <md-button @click="deletar(item)" class="md-icon-button md-raised md-accent">
            <md-icon>delete_outline</md-icon>
          </md-button>
        </md-table-cell>
      </md-table-row>
    </md-table>
  </div>
</template>

<script>
import Usuarios from "../service/usuario"
import Cargos from "../service/cargo"
export default {
  name: "usuario",
  showModal: false,
  data: () => ({
    showModal: false,
    novo: false,
    active: false,
    cargoObject:{
      id: null,
      nome: null,
    },
    cargo: {
      id: null,
      nome: null,
    },
    usuario: {
      id: null,
      nome: null,
      cpf: null,
      dataNascimento: null,
      sexo: null,
      dataCadastro: null,
      cargo: {
        id: null,
        nome: null,
      },
    },
    dataNaoFormatada: null,
    usuarios: [],
    cargos: [],
    errors: [],
  }),
  mounted() {
    this.listar()
    this.listarCargos()
  },
  methods: {
    abreModal(novo) {
      if (novo) {
        this.usuario.id = null
        this.usuario.nome = null
        this.usuario.cpf = null
        this.usuario.dataNascimento = null
        this.usuario.sexo = null
        this.usuario.dataCadastro = null
        this.usuario.cargo.id = null
      }
      this.showModal = true
    },
    listar() {
      Usuarios.list().then((res) => {
        this.usuarios = res.data
      })
    },
    listarCargos() {
      Cargos.list().then((res) => {
        this.cargos = res.data
      })
    },
    salvar() {
      if (!this.usuario.id) {
        Usuarios.save(this.usuario)
          .then((res) => {
            alert(`${this.usuario.nome} inserido com sucesso!`)
            this.usuarios = {}
            this.errors = []
            this.showModal = false
            this.listar()
          })
          .catch((e) => {
            if (e.response.data.error) {
              this.errors = {
                error: "Ocorreu um erro ao salvar o usuário!",
              }
            }
            this.showModal = false
            this.listar()
          })
      } else {   
          this.usuario.cargo = this.cargoObject
        Usuarios.update(this.usuario)
          .then((res) => {
            alert(`${this.usuario.nome} atualizado com sucesso!`)
            this.usuarios = {}
            this.errors = []
            this.showModal = false
            this.listar()
          })
          .catch((e) => {
            if (e.response.data.error) {
              this.errors = {
                error: "Ocorreu um erro ao editar o usuário!",
              }
            }
            this.showModal = false
            this.listar()
          })
      }
      this.novo = true
    },
    editar(usuario) {
      this.usuario = usuario
      this.abreModal(false)
      this.listar()
    },
    deletar(usuario) {
      if (confirm(`Tem certeza que deseja apagar ${usuario.nome}?`)) {
        Usuarios.delete(usuario.id)
          .then((res) => {
            alert(`Cargo ${usuario.nome} foi apagado.`);
            this.showModal = false;
            this.errors = [];
            this.listar();
          })
          .catch((e) => {
            if (e.response.data.error) {
              this.errors = {
                error: "Ocorreu um erro ao editar o usuário!",
              }
            }
            this.showModal = false;
          });
      }
    },
    buscarCargo(id){
        Cargos.search(id).then(res => {
            this.cargoObject = res.data
        }).cath(e => {
            if (e.response.data.error) {
              this.errors = {
                error: "Ocorreu um erro ao editar o usuário!",
              }
            }
            this.showModal = false;
        })
    },
    formataData(data) {
      this.usuario.dataNascimento = new Date(data).toLocaleDateString("pt-BR", {
        timeZone: "UTC",
      })
    },
  },
}
</script>

<style>
.md-select-menu {
  z-index: 1051 !important;
  top: -17em !important;
  position: relative !important;
}
</style>
