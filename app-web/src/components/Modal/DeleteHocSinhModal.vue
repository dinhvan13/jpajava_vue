<template>
  <v-dialog v-model="dialogAlert" max-width="500px">
    <v-form ref="deleteHS">
      <v-card>
        <v-card-title class="text-h5 error white--x">{{ formTitle }}</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="deleteItemConfirm(hocsinh.id)">OK</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-dialog>
</template>
<script>
import axios from "axios";
//
//import {EventBus} from "@/main.js"
export default {
  data: () => ({
    //dialog : false,
    formTitle: null,
    dialogAlert: false,
    hocsinh: {
    
    },
    defaultItem: {
      id: null,
      hoTen: "",
      lop: {
        id: null,
      },
      namSinh: null,
      queQuan: null,
    },
    valid: null,
  }),
  created() {
    //EventBus.$on("deleteDialog",this.deleteDialog);
  },
  methods: {
    XoaHocSinh(id) {
      axios
        .delete(`http://localhost:8080/hocsinh/xoa?id=${id}`)
        .then((res) => {
          this.$emit("refreshTable");
          //console.log(this.getData);
          console.log(res.data);
          alert(res.data);
        })
        .catch((error) => {
          if (error.response) {
            alert(error.response.data);
          }
        });
    },
    closeDelete() {
      this.dialogAlert = false;
    },
    deleteItemConfirm() {

        this.XoaHocSinh(this.hocsinh.id);
        this.dialogAlert = false;
    },
    deleteModal(val, obj) {
      //console.log(obj);
      if (val == "delete" && obj != null) {
        this.formTitle = "Are you sure you want to delete this item?";
        this.hocsinh = obj;

      }
      this.dialogAlert = !this.dialogAlert;
    },
  },
};
</script>
