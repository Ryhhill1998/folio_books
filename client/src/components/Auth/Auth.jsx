import LoginScreen from "./LoginScreen/LoginScreen";
import RegisterScreen from "./RegisterScreen/RegisterScreen";
import "./Auth.scss";

const Auth = () => {
  return (
    <div className="auth-container">
      <LoginScreen />

      <p className="or-connector">OR</p>

      <RegisterScreen />
    </div>
  );
};

export default Auth;
