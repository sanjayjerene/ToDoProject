//  for login, register, and todos pages
const SERVER_URL = "http://localhost:8080";


function login() {
    // Get email and password from input fields
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value;

    // Validate inputs
    if (!email || !password) {
        alert("Please enter both email and password.");
        return;
    }

    // Send login request to backend
    fetch(`${SERVER_URL}/auth/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    })
    .then(async (response) => {
        // Parse JSON safely
        const data = await response.json().catch(() => ({}));

        // Handle errors from backend
        if (!response.ok) {
            throw new Error(data.message || "Login failed");
        }

        // Save JWT token to localStorage
        localStorage.setItem("token", data.token);

        // Redirect to todos page
        window.location.href = "todos.html";
    })
    .catch((error) => {
        alert(`Error: ${error.message}`);
    });
}


// Register page logic
function register() {
    
     const email = document.getElementById("email").value;
    const password=document.getElementById("password").value;

    fetch(`${SERVER_URL}/auth/register`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({email,password})
    })
    .then(response=>  {
                 if(response.ok){
                    alert("Registration successful! Please log in.");
                    window.location.href = "index.html";
                 }  

                 else
                    {
                        return response.json().then(data=>{ throw new Error(data.message || "Registration failed")}); 

                }
            }).catch( error=>{
                alert(`Error: ${error.message}`);

            })

}
// Todos page logic
function createTodoCard(todo) {
        const card = document.createElement("div");
        card.className = "todo-card";

        const checkbox= document.createElement("input");
        checkbox.type="checkbox";
        checkbox.checked=todo.isCompleted;    
        checkbox.addEventListener("change",function(){
            const updatedTodo = {...todo,isCompleted: checkbox.checked}
            updateTodoStatus(updatedTodo);
        }); 

        const span = document.createElement("span");
        span.textContent=todo.title;
        if(todo.isCompleted){
            span.style.textDecoration="line-through";
            span.style.color="gray";
        }

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent="X";
        deleteBtn.onclick=function(){
            deleteTodo(todo.id);
        }

        card.appendChild(checkbox);
        card.appendChild(span);
        card.appendChild(deleteBtn);    

        return card;


} 

// Example in loadTodos()
function loadTodos() {
    const token = localStorage.getItem("token"); // read fresh
    if (!token) {
        alert("Please log in first.");
        window.location.href = "index.html";
        return;
    }

    fetch(`${SERVER_URL}/todo/v1/get`, {
        method: "GET",
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
    .then(async (response) => {
        const todos = await response.json().catch(() => []);
        if (!response.ok) throw new Error("Failed to get todos");
        
        const todoList = document.getElementById("todo-list");
        todoList.innerHTML = "";

        if (!todos || todos.length === 0) {
            todoList.innerHTML = "<p>No todos found. Add a new todo!</p>";
        } else {
            todos.forEach(todo => todoList.appendChild(createTodoCard(todo)));
        }
    })
    .catch(error => {
        alert(`Error: ${error.message}`);
        document.getElementById("todo-list").innerHTML = "<p>Error loading todos.</p>";
    });
}


function addTodo() {
    const token = localStorage.getItem("token"); // read fresh

        const input = document.getElementById("new-todo");
        const todotext = input.value.trim();
      fetch(`${SERVER_URL}/todo/v1`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${token}`
            },

            body:JSON.stringify({title:todotext,isCompleted :false})
            
    })
    .then(response=>  {
                 if(!response.ok){
 throw new Error(data.message || " failed to add Todo"); 

                }
                return response.json();
            })
          
            .then((newtodo)=>{
                input.value="";

                loadTodos()

            })  

            .catch( error=>{
                alert(`Error: ${error.message}`);

            })

}

function updateTodoStatus(todo) {
    const token = localStorage.getItem("token");
  fetch(`${SERVER_URL}/todo/v1/${id}`,{
            method:"PUT",
            headers:{
                "Content-Type":"application/json",
                Authorization:`Bearer ${token}`
            },

            body:JSON.stringify(todo)
            
    })
    .then(response=>  {
                 if(!response.ok){
 throw new Error(data.message || " failed to update"); 

                }
                return response.json();
            })
          
            .then(()=>loadTodos())  

            .catch( error=>{
                alert(`Error: ${error.message}`);

            })
}

function deleteTodo(id) {
const token = localStorage.getItem("token");
        fetch(`${SERVER_URL}/todo/v1/${id}`,{
            method:"DELETE",
            headers:{
                Authorization:`Bearer ${token}`
            },
            
    })
    .then(response=>  {
                 if(!response.ok){
 throw new Error(data.message || " failed to delete Todo"); 

                }
                return response.text();
            })
          
            .then(()=>loadTodos())  

            .catch( error=>{
                alert(`Error: ${error.message}`);

            })
}

// Page-specific initializations
document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById("todo-list")) {
        loadTodos();
    }
});