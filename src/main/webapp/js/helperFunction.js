function getUserById(userId){
	console.log("get user",userList)
	const userName = userList.filter((user) => user.userId == userId)[0].username;
	return userName;
}