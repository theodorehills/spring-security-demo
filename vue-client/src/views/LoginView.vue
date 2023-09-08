<template>
  <h1>Welcome to login to our system!</h1><br>
  Username: <input type="text" v-model="username" /><br />
  Password: <input type="text" v-model="password" /><br />
  <button @click="handleLogin">Login</button>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import axios from "axios"

const username = ref<string>('')
const password = ref<string>('')

function handleLogin() {
  axios.post(`http://127.0.0.1:8080/login`, {
    username: username.value,
    password: password.value
  }, {
    headers: {
      // You have to use application/x-www-form-urlencoded to login.
      "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8"
    }
  }).then((response: unknown) => {
    console.log(response)
  })
}
</script>

<style scoped></style>
