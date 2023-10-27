import { useState } from "react";
import "./RegisterScreen.scss";
import { useProfileStore } from "../../../globalStores/profile";

const RegisterScreen = () => {
  // const [name of variable, name of function to set variable value] = useState(default value);
  const [forename, setForename] = useState("");
  const [surname, setSurname] = useState("");
  const [email, setEmail] = useState(""); // state creates a new variable and a method to update the variable
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const registerUser = useProfileStore((store) => store.registerUser);
  const error = useProfileStore((store) => store.registerError);

  const handleForenameChange = (newForename) => {
    // validate display name check
    setForename(newForename);
  };

  const handleSurnameChange = (newSurname) => {
    // validate display name check
    setSurname(newSurname);
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
    await registerUser(forename, surname, email, password);
  };

  return (
    <div className="form-container">
      <h2>Register for a new account</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Forename"
          value={forename}
          onChange={({ target }) => handleForenameChange(target.value)}
        />

        <input
          type="text"
          placeholder="Surname"
          value={surname}
          onChange={({ target }) => handleSurnameChange(target.value)}
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

        {error && <p className="error-message">{error.message}</p>}

        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default RegisterScreen;
