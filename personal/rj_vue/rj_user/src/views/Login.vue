<template>
    <div>
        <el-form
                :rules="rules"
                ref="loginForm"
                v-loading="loading"
                element-loading-text="正在登录..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
                :model="loginForm"
                class="loginContainer">
            <h3 class="loginTitle">人际关系追踪系统登录</h3>
            <el-form-item prop="username">
                <el-input size="normal" type="text" v-model="loginForm.username" auto-complete="off"
                          placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item prop="password">
                <el-input size="normal" type="password" v-model="loginForm.password" auto-complete="off"
                          placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item prop="code">
                <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                          placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 250px"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="" style="cursor: pointer">
            </el-form-item>
<!--          <div >-->
<!--            <el-radio v-model="radio1" label="1"  >用户</el-radio>-->
<!--            <el-radio v-model="radio1" label="2" >管理员</el-radio>-->
<!--          </div>-->
           <div>
<!--            <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">用户登录</el-button>-->
             <el-button style="width: 45%" type="primary" :loading="loading" @click="submitLogin">
              用户登录
             </el-button>
             <el-button style="width: 45%" type="primary" icon="el-icon-plus" @click="register">
               注册
             </el-button>

          </div>
         <a href="">忘记密码</a>
        </el-form>

    </div>
</template>

<script>

    export default {
        name: "Login",
        data() {
            return {

                loading: false,
              // -----------------------
                dialogVisible: false,
                vcUrl: '/verifyCode?time='+new Date(),
                loginForm: {
                    username: 'admin',
                    password: '123',
                    code:''
                },
                checked: true,
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                    code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
                },
              myuser: {
                Sno: "javaboy",
                Spwd: "男",
                // birthday: "1989-12-31",
                // idCard: "610122199001011256",
                // wedlock: "已婚",
                // nationId: 1,
                // nativePlace: "陕西",
                // politicId: 13,
                // email: "laowang@qq.com",
                // phone: "18565558897",
                // address: "深圳市南山区",
                // departmentId: null,
                // jobLevelId: 9,
                // posId: 29,
                // engageForm: "劳务合同",
                // tiptopDegree: "本科",
                // specialty: "信息管理与信息系统",
                // school: "深圳大学",
                // beginDate: "2017-12-31",
                // workState: "在职",
                // workID: "00000057",
                // contractTerm: 2,
                // conversionTime: "2018-03-31",
                // notworkDate: null,
                // beginContract: "2017-12-31",
                // endContract: "2019-12-31",
                // workAge: null
              },
            }
        },
        methods: {
            updateVerifyCode() {
                this.vcUrl = '/verifyCode?time='+new Date();
            },
            submitLogin() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.postRequest('/doLogin', this.loginForm).then(resp => {
                            this.loading = false;
                            if (resp) {
                                this.$store.commit('INIT_CURRENTHR', resp.obj);
                                window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
                                let path = this.$route.query.redirect;
                                  // if ()  xxxxxx
                                this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
                              //   else{
                              //
                              // }
                            }else{
                                this.vcUrl = '/verifyCode?time='+new Date();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            },
           register(){
             this.emptyUser();
             this.title = '注册用户';
             this.dialogVisible = true;
           },
          emptyUser(){
              this.myuser = {
                Sno: "",
                Spwd: "",
                // birthday: "",
                // idCard: "",
                // wedlock: "",
                // nationId: 1,
                // nativePlace: "",
                // politicId: 13,
                // email: "",
                // phone: "",
                // address: "",
                // departmentId: null,
                // jobLevelId: 9,
                // posId: 29,
                // engageForm: "",
                // tiptopDegree: "",
                // specialty: "",
                // school: "",
                // beginDate: "",
                // workID: "",
                // contractTerm: 2,
                // conversionTime: "",
                // notworkDate: null,
                // beginContract: "",
                // endContract: "",
                // workAge: null
              }
              // this.inputDepName = '';
            },
          }





    }
</script>

<style>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #505458;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }
    .el-form-item__content{
        display: flex;
        align-items: center;
    }
</style>
