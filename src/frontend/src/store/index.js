import Vue from 'vue'
import vuex from 'vuex'
import loan from './loan'
import member from "./member"
import calculator from "./calculator";
Vue.use(vuex)
export const store = new vuex.Store({
    modules: {
        loan, member, calculator
    }
})