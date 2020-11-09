new Vue({
    el: '#app',
    data() {
        return {
            dialogxxx: true,
            avatarDialogxxx: false,
            formxxx: {},
            avatarListxxx: []
        }
    },
    mounted() {
        this.$refs.loader.style.display = 'none';
    },
    methods: {
        handleEditAvatorxxx() {
            this.$http.get(api.getAvatarList()).then(response => {
                this.avatarListxxx = response.body;
            });
            this.avatorDialogxxx = true;
        },
        changeAvatarxxx(url) {
            this.form.avatarxxx = url;
            this.avatorDialogxxx = false;
        },

        login(form) {
            this.$refs[form].validate(valid => {
                if (valid) {
                    if (this.form.avatarxxx == null || this.form.avatarxxx == '') {
                        this._notify('请选择头像', 'warning')
                        return;
                    }
                    this.form.idxxx = new Data().getTime();
                    this.$http.post(api.login(), JSON.stringify(this.form()).then(response => {
                        console.log(response)
                        if (response.body.code == 200) {
                            window.location.href = api.redirect(this.form.idxxx)
                        } else {
                            this.$message({
                                message: response.body.msg,
                                type: 'error'
                            })
                        }


                    }))
                } else {
                    return false;
                }

            })

        }
    }


})