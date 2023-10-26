import { useState } from "react";
import "./RegisterScreen.scss";
import { useProfileStore } from "../../../globalStores/profile";

const RegisterScreen = () => {
  // const [name of variable, name of function to set variable value] = useState(default value);
  const [displayName, setDisplayName] = useState("");
  const [email, setEmail] = useState(""); // state creates a new variable and a method to update the variable
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const registerUser = useProfileStore((store) => store.registerUser);

  //   passing in the email from the user input and updating state
  const handleDisplayNameChange = (newDisplayName) => {
    // validate display name check
    setDisplayName(newDisplayName);
  };

  const handleEmailChange = (newEmail) => {
    // validate email check
    setEmail(newEmail);
  };

  const handlePasswordChange = (newPassword) => {
    // validate password check
    setPassword(newPassword);
  };

  const handleConfirmPasswordChange = (newConfirmPassword) => {
    // validate password check
    setConfirmPassword(newConfirmPassword);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    // pass email and password to backend for verification
    // post request to send email and password
    await registerUser(displayName, email, password);
  };

  return (
      <div className="form-container">
        <h2>Register for a new account</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Display name"
            value={displayName}
            onChange={({ target }) => handleDisplayNameChange(target.value)}
          />

          <input
            type="text"
            placeholder="Email"
            value={email}
            onChange={({ target }) => handleEmailChange(target.value)}
          />

          <input
            type="text"
            placeholder="Password"
            value={password}
            onChange={({ target }) => handlePasswordChange(target.value)}
          />

          <input
            type="text"
            placeholder="Confirm password"
            value={confirmPassword}
            onChange={({ target }) => handleConfirmPasswordChange(target.value)}
          />

          <button type="submit">Register</button>
        </form>
      </div>
  );
};

export default RegisterScreen;
