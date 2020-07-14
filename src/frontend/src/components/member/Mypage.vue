<template>
    <div>
        <v-overlay :absolute="absolute" :value="showUpdateForm" :opacity="opacity">
            <v-card  style="width: 350px; min-height: 450px; padding: 8%;" class="elevation-12" light>
                <h2 style="float: left">비밀번호 변경</h2><v-icon style="float: right" large @click="escapeUpdateForm">{{close}}</v-icon><br>
                <h3  style="padding-top: 50px;">현재 비밀번호를 입력하세요.</h3>
                <v-text-field label="4자 이상 숫자 또는 문자"  type="password" v-model="currentPassword"></v-text-field>
                <h3>변경할 비밀번호를 입력하세요.</h3>
                <v-text-field label="4자 이상 숫자 또는 문자"  type="password" v-model="updatePassword" @keyup.enter="tryUpdate"></v-text-field>
                <v-alert dense outlined type="error" v-if="updateFail">비밀번호를 확인해주세요.</v-alert>
                <v-btn color="#26A69A" style="width: 100%; height: 50px; margin-top: 30px" :disabled = disabled @click="tryUpdate"><h3>변경하기</h3></v-btn>
            </v-card>
        </v-overlay>
        <v-overlay :absolute="absolute" :value="showDeleteForm" :opacity="opacity">
                <v-card  style="width: 350px; min-height: 450px; padding: 8%; text-align: center" class="elevation-12" light>
                    <h2 style="float: left">계정 삭제</h2><v-icon style="float: right" large @click="escapeDeleteForm">{{close}}</v-icon><br>
                    <h3  style="padding-top: 50px;">현재 비밀번호를 입력하세요.</h3>
                    <v-text-field style="margin-top: 30px" light v-model="deletePassword" label="현재 비밀번호" type="password"></v-text-field>
                    <v-alert  dense   type="info" v-if="deleteFail" >
                        비밀번호가 틀렸습니다.
                    </v-alert>
                    <v-btn style="margin-top: 20px; width: 80%" large color="error" @click="deleteAccount" :disabled="disabled">회원탈퇴하기</v-btn>
                </v-card>
        </v-overlay>
<MainLayout>

    <template #header>
        <div id="header">
        <h1>마이페이지</h1>
        </div>
    </template>
    <template #content>
        <v-tabs fixed-tabs>
            <v-tab
                    v-for="item in items"
                    :key="item"
                    @click="enterTab(item)"
            >
                {{ item }}
            </v-tab>
        </v-tabs>
        <div id="notificationTab" v-if="showNotificationTab">설정하신 알림이 없습니다.</div>
        <div id="reviewTab" v-else-if="showReviewTab">작성하신 리뷰가 없습니다.</div>
        <div id="myInfomation" v-else-if="showMyInfomation">
            <div v-if="login">
            <div class="title">계정정보</div>
            <div>이메일 : {{member}}</div>
            <div style="float: left">비밀번호 : ******</div>
                <div style="color:dodgerblue; float: left; padding-left: 10px" @click="enterUpdateForm">비밀번호 변경</div><br>
            <v-btn style="margin-top: 5px" @click="enterDeleteForm">회원탈퇴하기</v-btn>
            </div>
            <div v-else>
                로그인이 필요한 서비스입니다.
            </div>
        </div>
    </template>

    <template #footer></template>

</MainLayout>
    </div>
</template>

<script>
    import MainLayout from "../layout/MainLayout";
    import {mapState} from 'vuex'
    import {mdiClose} from '@mdi/js'
    export default {
        components : {MainLayout},
        data(){
            return {
                items:['알림','리뷰','내 정보'],
                currentPassword: '',
                updatePassword: '',
                deletePassword : '',
                disabled : true,
                showNotificationTab : true,
                showReviewTab : false,
                showMyInfomation : false,
                showUpdateForm : false,
                absolute : false,
                opacity : 0.9,
                showDeleteForm : false,
                close : mdiClose
            }
        },
        methods : {
            enterTab(item){
                switch (item) {
                    case '알림': this.showNotificationTab=true; this.showReviewTab=false;  this.showMyInfomation=false; break;
                    case '리뷰': this.showReviewTab=true;  this.showNotificationTab=false; this.showMyInfomation=false; break;
                    case '내 정보': this.showMyInfomation=true; this.showNotificationTab=false;  this.showReviewTab=false; break;
                }
    },
            deleteAccount(){
                this.$store.dispatch('member/delete',{email : localStorage.getItem("email"),
                                                                                                                                        password : this.deletePassword})
            },
            tryUpdate(){
                    this.$store.dispatch('member/update', { email : localStorage.getItem("email"),
                        currentPassword: this.currentPassword,
                        updatePassword: this.updatePassword})
            },
            enterUpdateForm(){
               this.showUpdateForm = true
                this.$store.state.member.updateFail=false
            },
            escapeUpdateForm(){
                this.showUpdateForm = false
                this.$store.state.member.updateSuccess=false
            },
            enterDeleteForm(){
                this.showDeleteForm = true
                this.$store.state.member.deleteFail=false
            },
            escapeDeleteForm(){
                this.showDeleteForm = false
                this.$store.state.member.deleteSuccess=false
            }
        },
        computed : {
            ...mapState({
                member : (state)=> state.member.member,
                login : (state)=>state.member.login,
                updateSuccess : (state)=>state.member.updateSuccess,
                deleteSuccess : (state)=>state.member.deleteSuccess,
                updateFail : (state)=>state.member.updateFail,
                deleteFail : (state)=>state.member.deleteFail
            })
        },
        watch : {
            updatePassword(){
                if(this.currentPassword!=='' && this. updatePassword!==''){this.disabled=false}
                else{this.disabled=true}
            },
            deletePassword(){
                if (this.deletePassword!==''){this.disabled=false}
                else{this.disabled=true}
            },
            updateSuccess(){
                if(this.updateSuccess === true){ this.escapeUpdateForm()}
            },
            deleteSuccess(){
                if (this.deleteSuccess===true){this.escapeDeleteForm()}
            }
        }
    }
</script>

<style scoped>
    #header {
        padding-top: 50px;
        padding-left: 50px;
        text-align: center;
    }
#notificationTab{
    width: 100%;
    text-align: center;
    padding-top: 100px;
}
    #reviewTab{
        width: 100%;
        text-align: center;
        padding-top: 100px;
    }
    #myInfomation{
        width: 50%;
        margin: 0 auto;
        padding-top: 100px;
    }

</style>