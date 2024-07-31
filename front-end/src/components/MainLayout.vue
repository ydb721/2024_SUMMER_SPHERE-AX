<template>
    <div>
        <Navbar />
        <div id="show-routes">
            <router-view />
        </div>
    </div>
</template>

<script>
import Navbar from './Navbar';

export default {
    name: 'MainLayout',
    components: {
        Navbar
    }
};
</script>

<style>
body {
    background-color: black;
    color: red
}

#show-routes {
    padding-left: 10%;
    padding-right: 10%;
}
</style>