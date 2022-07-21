<script>
import axios from "axios";

export default {
  name: "LogoutView",
  created() {
    axios.post("/api/logout").then(() => {
      this.$store.commit("accountStore/logout");
      this.$router.push("/");
    });
  },
};
</script>
