import Vue from 'vue'
import Router from 'vue-router'
import LoanHome from "../components/loan/LoanHome";
import Main from "../components/Main";
import CreditLoanList from "../components/loan/CreditLoanList";
import Mypage from "../components/member/Mypage";
import Detail from "../components/loan/Detail";
import Calculator from "../components/calculator/Calculator";
Vue.use(Router)

export default new Router({
    mode : 'history',
    routes : [
        {path : '/loanHome' ,component : LoanHome },
        {path : '/', component: Main},
        {path : '/creditLoanList', component: CreditLoanList},
        {path: '/mypage', component: Mypage},
        {path : '/detail', component: Detail},
        {path: '/calculator', component: Calculator}
    ],

})