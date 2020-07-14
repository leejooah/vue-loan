<template>
<v-app>

    <v-system-bar color="blue-grey darken-3"
                  app = "true"></v-system-bar>
    <v-app-bar
            app
            color="white"
            :elevation="0"
    >
        <v-toolbar-title @click="returnHome" class = "display-1">LOAN</v-toolbar-title>
        <ul class="menu" >
            <li @click="enterMenu(menu)" v-for="menu of menus" :key="menu">{{menu}}</li></ul>
        <v-spacer></v-spacer>
        <div v-if="login">
            <div @click="enterMyPage" style="float: left">마이페이지</div><div  style="float: left; padding-left: 20px;padding-right: 20px">|</div><div  style="float: left" @click="logout">로그아웃</div></div>
        <div v-else>
            <div @click="enterLoginForm" style="float: left">로그인</div><div style="float: left; padding-left: 20px;padding-right: 20px">|</div><div style="float: left" @click="enterRegisterForm">회원가입</div>
        </div>
    </v-app-bar>
    <div>
    <v-overlay
            :absolute="absolute"
            :value="showLoginForm"
            :opacity="opacity"
    >
        <v-card  style="width: 350px; min-height: 450px; padding: 8%;" class="elevation-12" light>
            <h2 style="float: left">로그인</h2><v-icon style="float: right" large @click="escapeLoginForm">{{close}}</v-icon><br>
            <h3  style="padding-top: 50px;">이메일을 입력하세요</h3>
            <v-text-field label="test@test.com"  type="text" v-model="email"></v-text-field>
           <h3>비밀번호를 입력하세요</h3>
            <v-text-field label="4자 이상 숫자 또는 문자"  type="password" v-model="password" @keyup.enter="tryLogin"></v-text-field>
            <v-alert dense outlined type="error" v-if="fail">이메일과 비밀번호를 확인해주세요.</v-alert>
            <v-btn color="#26A69A" style="width: 100%; height: 50px; margin-top: 30px" :disabled = disabled @click="tryLogin"><h3>로그인</h3></v-btn>
        </v-card>
    </v-overlay>
        <v-overlay
                :absolute="absolute"
                :value="showRegisterForm"
                :opacity="opacity"
        >
            <v-card  style="width: 350px; min-height: 450px; padding: 8%;" class="elevation-12" light>
                <h2 style="float: left">회원가입</h2><v-icon style="float: right" large @click="escapeRegisterForm">{{close}}</v-icon><br>
                <h3  style="padding-top: 50px;">이메일을 입력하세요</h3>
                <v-text-field label="test@test.com"  type="text" v-model="joinEmail"></v-text-field>
                <h3>비밀번호를 입력하세요</h3>
                <v-text-field label="4자 이상 숫자 또는 문자"  type="password" v-model="joinPassword"></v-text-field>
                <v-alert dense outlined type="error" v-if="duplication">이미 가입된 이메일입니다.</v-alert>
                <v-btn color="#26A69A" style="width: 100%; height: 50px; margin-top: 30px" :disabled = disabled @click="register"><h3>회원가입</h3></v-btn>
            </v-card>
        </v-overlay>

    </div>

    <v-content>
        <v-btn @click="goTo" style=" position : fixed; top: 80%; left: 92%;" class="mx-2" fab large dark color="#26A69A">
            <v-icon  dark>{{up}}</v-icon>
        </v-btn>

<router-view/>
    </v-content>





</v-app>
</template>

<script>
    import router from '@/router'
    import {mapState} from 'vuex'
    import {mdiClose} from '@mdi/js'
    import { mdiAppleKeyboardControl } from '@mdi/js';
    export default {
        data(){
            return {
                email : '',
                password:'',
                joinEmail : '',
                joinPassword : '',
                opacity: 0.9,
                absolute: false,
                showRegisterForm: false,
                showLoginForm:false,
                menus : ['대출','계산기','카드','신용','보험',' 이벤트'],
                items: [{item : '대출 홈'},{ item : '내 계산기'},{item : '상품 홈'}],
                close : mdiClose,
                up : mdiAppleKeyboardControl,
                disabled : true
            }
        },
        methods : {
          enterMenu(menu){
            if(menu==='대출') {
                router.push('/loanHome')
            }else if(menu==='계산기'){
                router.push('/calculator')
            }else{
                alert('준비 중입니다')
            }
          },
            returnHome(){
              router.push('/')
            },
            enterMyPage(){
              router.push('/mypage')
            },
            escapeRegisterForm(){
              this.$store.state.member.showCompleteMessage=false
                this.showRegisterForm=false
                this.$store.state.member.duplication=false
            },
            escapeLoginForm(){
                this.showLoginForm=false
                this.$store.state.member.fail=false
            },
            enterRegisterForm(){
              this.showRegisterForm=true
                this.$store.state.member.duplication=false
            },
            register(){
                    this.$store.dispatch('member/register',{email : this.joinEmail,
                        passwd : this.joinPassword})
            },
            enterLoginForm(){
                this.$store.state.member.fail=false
                this.showLoginForm=true
            },
            tryLogin(){
                    this.$store.dispatch('member/login',{email : this.email,
                        passwd : this.password})
            },
            logout(){
                this.$store.dispatch('member/logout')
            },
            goTo(){
              window.scrollTo(0,0)
            }
        },
        computed : {
            ...mapState({
                registerSuccess: (state) => state.member.registerSuccess,
                duplication : (state)=>state.member.duplication,
                fail : (state)=>state.member.fail,
                login : (state)=>state.member.login
            })
        },
        watch : {
            password(){
                if(this.email!=='' && this.password!==''){this.disabled=false}
                else{this.disabled=true}
            },
            joinPassword(){
                if(this.joinEmail!=='' && this. joinPassword!==''){this.disabled=false}
                else{this.disabled=true}
            },
                login(){
                    if(this.login===true){ this.escapeLoginForm()}
                },
            registerSuccess(){
                if(this.registerSuccess===true){ this.escapeRegisterForm()}
            }

        }

    }
</script>

<style scoped>
    space{
        padding: 15px;
    }
    .menu li    {float: left;
        padding-left: 50px;
        width: auto;
        list-style: none}
.sidemenu li {
    list-style: none;
   padding-left: 50px;
    padding-top : 10px;
}
    form{

    }



</style>