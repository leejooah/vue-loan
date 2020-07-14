<template>
    <div class = wrapper>
        <header id = "header">
            <slot name="header"></slot>
        </header>
        <section id="content">
            <slot name="content"></slot>
        </section>
        <footer id="footer">
            <slot name="footer" :footer="footer"></slot>
        </footer>
    </div>
</template>

<script>
    export default {
    }
</script>

<style scoped>
    /* 전체 구조 */
    wrapper {
        width: 100%;
        margin: 0 auto;
    }
    header {
        width : 100%;
        min-height: 200px;
    }
    #content {
        width: 100%;
        min-height: 500px;
        margin : 0 auto
    }

    footer {
        clear: both;
        height: 300px;
        background-color: #26A69A;
    }
</style>