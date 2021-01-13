<template>
  <div style="height: 600px;width: 500px;float: left">
    <div style="margin-top: 3%;margin-left: 7%;width: 400px">
      <i-form ref="formValidate" :model="formValidate" :rules="ruleValidate" :label-width="80":disabled="isdisabledFn">
        <form-item label="姓名" prop="name">
          <i-input v-model="formValidate.sname" placeholder="Enter your name" style="width: 200px"></i-input>
        </form-item>
        <form-item label="年龄" prop="age">
          <i-input v-model="formValidate.age" placeholder="Enter your age" style="width: 200px"></i-input>
        </form-item>
        <form-item label="电话" prop="call">
          <i-input v-model="formValidate.scall" placeholder="Enter your phone-number" style="width: 200px"></i-input>
        </form-item>
        <form-item label="学院" prop="collage" style="width: 300px" >
          <i-select v-model="formValidate.collage" placeholder="Select your collage">
            <i-option value="信息技术工程学院">信息技术工程学院</i-option>
            <i-option value="艺术学院">艺术学院</i-option>
            <i-option value="理学院">理学院</i-option>
          </i-select>
        </form-item>
        <form-item label="专业" prop="major" style="width: 300px">
          <i-select v-model="formValidate.major" placeholder="Select your major">
            <i-option value="软件工程">软件工程</i-option>
            <i-option value="计算机科学与技术">计算机科学与技术</i-option>
            <i-option value="数字媒体">数字媒体</i-option>
          </i-select>
        </form-item>
        <form-item label="班级" prop="sclass" style="width: 300px">
          <i-select v-model="formValidate.sclass" placeholder="Select your sclass">
            <i-option value="1701">1701</i-option>
            <i-option value="1702">1702</i-option>
            <i-option value="1702">1801</i-option>
            <i-option value="1702">1802</i-option>
          </i-select>
        </form-item>
        <form-item label="生日" >
          <row>
            <i-col span="11">
              <form-item prop="date">
                <date-picker type="date" placeholder="Select date" @on-change="time1" format="yyyy-MM-dd" ></date-picker>
              </form-item>
            </i-col>
          </row>
        </form-item>
        <form-item label="性别" prop="gender">
          <radio-group v-model="formValidate.gender">
            <radio label="1">男</radio>
            <radio label="0">女</radio>
          </radio-group>
        </form-item>
        <form-item >
          <i-button type="primary" @click="handleSubmit('formValidate')">Submit</i-button>
          <i-button @click="handleReset('formValidate')" style="margin-left: 8px">Reset</i-button>
        </form-item>
      </i-form>
    </div>
  </div>
</template>



// export default {
//   name: "Login",
//   data() {
//     return {
//       radio1: '1',
//       loading: false,
//       vcUrl: '/verifyCode?time='+new Date(),
//       loginForm: {
//         username: 'admin',
//         password: '123',
//         code:''
//       },
//       checked: true,
//       rules: {
//         username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
//         password: [{required: true, message: '请输入密码', trigger: 'blur'}],
//         code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
//       }
//     }
//   },
//   methods: {
//     updateVerifyCode() {
//       this.vcUrl = '/verifyCode?time='+new Date();
//     },
//     submitLogin() {
//       this.$refs.loginForm.validate((valid) => {
//         if (valid) {
//           this.loading = true;
//           this.postRequest('/doLogin', this.loginForm).then(resp => {
//             this.loading = false;
//             if (resp) {
//               this.$store.commit('INIT_CURRENTHR', resp.obj);
//               window.sessionStorage.setItem("user", JSON.stringify(resp.obj));
//               let path = this.$route.query.redirect;
//               // if ()  xxxxxx
//               this.$router.replace((path == '/' || path == undefined) ? '/home' : path);
//               //   else{
//               //
//               // }
//             }else{
//               this.vcUrl = '/verifyCode?time='+new Date();
//             }
//           })
//         } else {
//           return false;
//         }
//       });
//     },
//
//
//
//
//
//   }
// }


<script>
function newMsgCount() {
  $.ajax({
    url:'/newMsgCount',
    type:'post',
    success(res){
      mVue.countMsg=res;
      console.log(res);
    }
  })
}
function newRequestCount(){
  $.ajax({
    url:'/newRequestCount',
    type:'post',
    success(res) {
      mVue.countNewRequest=res;
      console.log(res);
    }
  })
}
var uu;

function f(){
  $.ajax({
    url:'/search',
    type:'post',
    success(res) {
      let msg=JSON.stringify(res);
      msg=$.parseJSON(msg);
      uu=msg;
      console.log(res);
      mVue.formValidate.sno=res.sno;
      if(res.sname==null){
        active("新用户!","请完善你的个人信息");
      }else{
        active(msg.sname);
      }
    }
  })
}
var mVue = new Vue({
  el:'#app',
  data(){
    const validatePhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else if (!/^1[34578]\d{9}$/.test(value)) {
        callback('手机号格式不正确');
      } else {
        callback();
      }
    };
    return{
      countMsg:'',
      countNewRequest:'',
      message:'',
      info:'',
      isCollapsed: false,
      choose:'',
      isdisabled:true,
      formValidate: {
        sno:'',
        sname: '',
        age:'',
        scall: '',
        collage: '',
        major:'',
        sclass:'',
        gender: '',
        birth: '',
      },
      ruleValidate: {
        name: [
          { required: true, message: '姓名不能为空', trigger: 'blur' }
        ],
        age: [
          { required: true, message: '年龄不能为空', trigger: 'blur' }
        ],
        call: [
          { required: true, validator:validatePhone, trigger: 'blur' },
        ],
        collage: [
          { required: true, message: '请选择学院', trigger: 'change' }
        ],
        major: [
          { required: true, message: '请选择专业', trigger: 'change' }
        ],
        sclass: [
          { required: true, message: '请选择班级', trigger: 'change' }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        date: [
          { required: true, type: 'date', message: '请选择日期', trigger: 'change' }
        ],
      }
    }
  },
  computed: {
    menuitemClasses: function () {
      return [
        'menu-item',
        this.isCollapsed ? 'collapsed-menu' : ''
      ]
    },
    isdisabledFn(){
      if(uu.sname!=null){
        this.formValidate.sname=uu.sname;
        this.formValidate.age=uu.sage;
        this.formValidate.scall=uu.scall;
        this.formValidate.collage=uu.collage;
        this.formValidate.major=uu.major;
        this.formValidate.sclass=uu.sclass;
        this.formValidate.birth=uu.birth;
        this.formValidate.gender=uu.gender;
        return this.isdisabled=true;
      }else{
        return this.isdisabled=false;
      }
    }
  },
  methods:{
    time1(e){
      this.formValidate.birth=e;
    },
    change(){
      mVue.choose="xxx";
    },
    handleSubmit (name) {
      this.$refs[name].validate((valid) => {
        if (valid) {
          this.$Message.success('Success!');
        } else {
          this.$Message.error('Fail!');
        }
      });
      let userInfo=this.formValidate;
      $.ajax({
        url:'/perfectInfo',
        type:'post',
        contentType: "application/json;charset=UTF-8",
        data:JSON.stringify(userInfo),
        success(res) {
          if (res==="ok") {
            //请求的是ｃｏｎｔｒｏｌｌｅｒ层
            window.location.href = "moreInfo";
          }
        }
      });
      console.log(JSON.stringify(userInfo))
    },
    handleReset (name) {
      this.$refs[name].resetFields();
    }
  }
});

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
