<template>
  <v-dialog v-model="dialogAlert" max-width="500px">
    <v-form ref="deleteLop">
      <v-card>
        <v-card-title class="text-h5 error white--x">{{ formTitle }}</v-card-title>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
          <v-btn color="blue darken-1" text @click="deleteItemConfirm(lop.id)">OK</v-btn>
          <v-spacer></v-spacer>
        </v-card-actions>
      </v-card>
    </v-form>
  </v-dialog>
</template>
<script>
import axios from "axios"
export default {
     data: () => ({
    //dialog : false,
    formTitle: null,
    dialogAlert: false,
    lop: {
    
    },
    defaultItem: {
      id: null,
      hoTen: "",
      siSo: 0,
    },
    valid: null,
  }),
  created() {
    //EventBus.$on("deleteDialog",this.deleteDialog);
  },
  methods: {
    XoaLop(id) {
      axios
        .delete(`http://localhost:8080/lop/xoa?id=${id}`)
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

        this.XoaLop(this.lop.id);
        this.dialogAlert = false;
    },
    deleteModal(val, obj) {
      //console.log(obj);
      if (val == "delete" && obj != null) {
        this.formTitle = "Are you sure you want to delete this item?";
        this.lop = obj;

      }
      this.dialogAlert = !this.dialogAlert;
    },
  },
}
</script>