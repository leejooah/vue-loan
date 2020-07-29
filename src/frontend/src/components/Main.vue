<template>
    <MainLayout>
        <template #header>
            <template>


            </template>
        </template>
        <template #content>
            <v-sheet style="background-color: white; height: 600px; text-align: center">
                <h1>페이지 메인</h1>
            </v-sheet>
            <v-sheet style="background-color: white; height: 600px">

            </v-sheet>
        </template>
        <template #footer>
        </template>

    </MainLayout>

</template>

<script>
    import MainLayout from "./layout/MainLayout";
    export default {
        components : {MainLayout},
        data(){
            return {
                colors: [
                    'indigo',
                    'warning',
                    'pink darken-2',
                    'red lighten-1',
                    'deep-purple accent-4',
                ],
                slides: [
                    'First',
                    'Second',
                    'Third',
                    'Fourth',
                    'Fifth',
                ],
            }
        }
    }
</script>

<style scoped>
</style>