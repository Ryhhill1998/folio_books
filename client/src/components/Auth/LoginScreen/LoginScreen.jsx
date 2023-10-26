import { useState } from "react";
import "./LoginScreen.scss";
import { useProfileStore } from "../../../globalStores/profile";

const LoginScreen = () => {
  // const [name of variable, name of function to set variable value] = useState(default value);
  const [email, setEmail] = useState(""); // state creates a new variable and a method to update the variable
  const [password, setPassword] = useState("");

  const signInUser = useProfileStore((store) => store.signInUser);
  const error = useProfileStore((store) => store.loginError);

  //   passing in the email from the user input and updating state
  const handleEmailChange = (newEmail) => {
    // validate email check
    setEmail(newEmail);
  };

  const handlePasswordChange = (newPassword) => {
    // validate password check
    setPassword(newPassword);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    // pass email and password to backend for verification
    // post request to send email and password
    await signInUser(email, password);
  };

  return (
    <div className="form-container">
      <h2>Login to an existing account</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(event) => handleEmailChange(event.target.value)}
        />

        <input
          type="text"
          placeholder="Password"
          value={password}
          onChange={(event) => handlePasswordChange(event.target.value)}
        />

        {error && <p className="error-message">{error.message}</p>}

        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginScreen;
