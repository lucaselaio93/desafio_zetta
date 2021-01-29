<template>
  <div>
    <div>
      <ul>
        <li v-for="(erro, index) of errors" :key="index">
          Erro no campo <b class="field">{{ erro.field }}</b
          >: {{ erro.defaultMessage }}
        </li>
        <li v-if="erroDelete">
          {{ erroDelete.error }}
        </li>
      </ul>
      <Modal v-model="showModal" title="Gerenciar Cargo">
        <form @submit.prevent="salvar">
          <md-input type="hidden" v-model="cargo.id"></md-input>

          <md-field>
            <label>Nome</label>
            <md-input v-model="cargo.nome"></md-input>
          </md-field>
          <md-card-actions>
            <md-button type="submit" class="md-primary">Salvar</md-button>
          </md-card-actions>
        </form>
      </Modal>

      <md-button type="submit" class="md-primary md-raised" @click="abreModal(true)"
        >Cadastrar Cargo</md-button
      >
    </div>
    <md-table v-model="cargos" md-sort="name" md-sort-order="asc" md-card>
      <md-table-toolbar>
        <h1 class="md-title">Cargos</h1>
      </md-table-toolbar>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="ID" md-numeric md-sort-by="id">{{
          item.id
        }}</md-table-cell>
        <md-table-cell md-label="Nome" md-sort-by="nome">{{ item.nome }}</md-table-cell>
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
import Cargos from "../service/cargo";
export default {
  name: "cargo",
  data: () => ({
    showModal: false,
    novo: false,
    active: false,
    cargo: {
      id: null,
      nome: null,
    },
    cargos: [],
    errors: [],
    erroDelete: null
  }),
  mounted() {
    this.listar();
  },
  methods: {
    abreModal(novo) {
      if (novo) {
        this.cargo.id = null;
        this.cargo.nome = null;
      }
      this.showModal = true;
    },
    listar() {
      Cargos.list().then((res) => {
        this.cargos = res.data;
      });
    },
    salvar() {
      if (!this.cargo.id) {
        Cargos.save(this.cargo)
          .then((res) => {
            alert(`${this.cargo.nome} inserido com sucesso!`);
            this.cargos = {};
            this.errors = [];
            this.erroDelete = null;
            this.showModal = false;
            this.listar();
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
            this.showModal = false;
            this.listar();
          });
      } else {
        Cargos.save(this.cargo)
          .then((res) => {
            alert(`${this.cargo.nome} atualizado com sucesso!`);
            this.cargos = {};
            this.errors = [];
            this.erroDelete = null;
            this.showModal = false;
            this.listar();
          })
          .catch((e) => {
            this.errors = e.response.data.errors;
            this.showModal = false;
            this.listar();
          });
      }
      this.novo = true;
    },
    editar(cargo) {
      this.novo = false;
      this.cargo = cargo;
      this.abreModal(false);
      this.listar();
    },
    deletar(cargo) {
      if (confirm(`Tem certeza que deseja apagar ${cargo.nome}?`)) {
        Cargos.delete(cargo.id)
          .then((res) => {
            alert(`Cargo ${cargo.nome} foi apagado.`);
            this.showModal = false;
            this.errors = [];
            this.erroDelete = null;
            this.listar();
          })
          .catch((e) => {
            if (e.response.data.error) {
              this.erroDelete = {
                error: "Não é possível excluir esse cargo pois o mesmo está vinculado à um usuário!",
              }
            }
            this.showModal = false;
          });
      }
    },
  },
};
</script>

<style>
.field {
  color: red;
}
</style>
