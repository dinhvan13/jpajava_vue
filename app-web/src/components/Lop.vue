<template>
  <v-container>
    <v-data-table :headers="headers" :items="dsLOP" sort-by="calories" class="elevation-1">
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>My Class CRUD</v-toolbar-title>
          <v-divider class="mx-4" inset vertical></v-divider>
          <v-text-field
          @keyup.enter ="searchName"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
            v-model="search"
          ></v-text-field>
          <v-spacer></v-spacer>
          <v-btn class="mx-2" fab dark color="blue" @click="openModal">
            <v-icon dark> mdi-plus </v-icon>
          </v-btn>
         
        </v-toolbar>
      </template>
      <template v-slot:[`item.actions`]="{ item }">
        <v-icon large secondary class="mr-2" color="teal" @click="openEditModal(item)">
          mdi-pencil
        </v-icon>
        <v-icon large color="red" @click="deleteModal(item)"> mdi-delete </v-icon>
      </template>
    </v-data-table>
    <lop-modal ref="ModalLop" @refreshTable="refreshTable" ></lop-modal>
    <DeleteLopModal ref="DeleteModalLop"  @refreshTable="refreshTable"/>
  </v-container>
</template>

<script>
import LopModal from "./Modal/LopModal.vue";
import DeleteLopModal from "./Modal/DeleteLopModal.vue";
import axios from "axios";
//import {EventBus} from "@/main.js"
export default {
  components: { LopModal,DeleteLopModal  },
  data: () => ({
    headers: [
      {
        text: "ID",
        align: "start",
        sortable: true,
        value: "id",
      },
      { text: "Name", value: "hoTen" },
      { text: "Count Students", value: "siSo" },
      { text: "Actions", value: "actions", sortable: false },
    ],
    dialogDelete: false,
    dsLOP: [],
    search:"",
    editedItem: {
      name: "",
      count: 0,
    },
    editedIndex: -1,
  }),

  computed: {
    /*formTitle() {
      return this.editedIndex === -1 ? "New Item" : "Edit Item";
    },*/
  },

  watch: {
   
  },

  created() {
    this.getData();
  },

  methods: {
    getData() {
      axios.get("http://localhost:8080/lop").then((response) => {
        this.dsLOP = response.data;
        //console.log(response.data)
      });
    },
    searchName(){
       axios.get(`http://localhost:8080/lop/timkiemlop?hoTen=${this.search}`).then((response) => {
        this.dsLOP = response.data;
        //console.log(response.data)
       // console.log("gggg")
      }).catch(error =>{
        console.log(error);
      })

           
       
    },
    
    openModal() {
      let item = null;
      //console.log("kfkdkdkdkd");
      this.$refs.ModalLop.openModal("add", item);
    },
    deleteModal(item){
      this.$refs.DeleteModalLop.deleteModal("delete",item);
    },
    openEditModal(item) {
      this.$refs.ModalLop.openModal("update", item);
    }
    ,
     refreshTable() {
      this.getData();
    },
  },
};
</script>
