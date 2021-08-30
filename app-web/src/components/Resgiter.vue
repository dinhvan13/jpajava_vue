<template>
  <div class="limiter">
    <div class="container-login100">
      <div class="login100-more" style="background-image: url('@/images/bg-01.jpg')"></div>

      <div class="wrap-login100 p-l-50 p-r-50 p-t-72 p-b-50">
        <v-form class="login100-form validate-form" v-model="user">
          <span class="login100-form-title p-b-59"> Sign Up </span>

          <div class="wrap-input100 validate-input" data-validate="Name is required">
            <span class="label-input100">Full Name</span>
            <input class="input100"  v-model="user.fullName" :rules="user.nameRules" type="text" name="name" placeholder="Name..." />
            <span class="focus-input100"></span>
          </div>

          <div
            class="wrap-input100 validate-input"
            data-validate="Valid email is required: ex@abc.xyz"
          >
            <span class="label-input100">Email</span>
            <input class="input100" v-model="email" required :rules="emailRules" type="text" name="email" placeholder="Email addess..." />
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Username is required">
            <span class="label-input100">Username</span>
            <input class="input100" type="text"  v-model="username" :rules="usernameRules" name="username" placeholder="Username..." />
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Password is required">
            <span class="label-input100">Password</span>
            <input class="input100" type="text" name="pass" placeholder="*************" />
            <span class="focus-input100"></span>
          </div>

          <div class="wrap-input100 validate-input" data-validate="Repeat Password is required">
            <span class="label-input100">Repeat Password</span>
            <input class="input100" type="text" name="repeat-pass" placeholder="*************" />
            <span class="focus-input100"></span>
          </div>

          <div class="flex-m w-full p-b-33">
            <div class="contact100-form-checkbox">
              <input
                class="input-checkbox100"
                id="ckb1"
                type="checkbox"
                v-model="checkbox"
                :rules="[(v) => !!v || 'You must agree to continue!']"
                required
                name="remember-me"
              />
              <label class="label-checkbox100" for="ckb1">
                <span class="txt1">
                  I agree to the
                  <a href="#" class="txt2 hov1"> Terms of User </a>
                </span>
              </label>
            </div>
          </div>

          <div class="container-login100-form-btn">
            <div class="wrap-login100-form-btn">
              <div class="login100-form-bgbtn"></div>
              <button class="login100-form-btn">Sign Up</button>
            </div>

            <router-link to="/" class="dis-block txt3 hov1 p-r-30 p-t-10 p-b-10 p-l-30">
              Sign in
              <i class="fa fa-long-arrow-right m-l-5"></i>
            </router-link>
          </div>
        </v-form>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      checkbox: false,
      user: {
        fullName: "",
         nameRules: [
        (v) => !!v || "Name is required!",
        (v) => (v && v.length <= 30) || "Name must be lass than 30 characters",
      ],

      },
    };
  },
};
</script>
