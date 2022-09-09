import './header.css';
import logo from '../../assets/img/logo.jpg';

const Header = () => {
  return (
    <>
      <header>
        <div className="dsmeta-logo-container">
          <img src={logo} alt="DSMeta" />
          <h1>DSMeta</h1>
          <p>
            Desenvolvido por
            <a href="https://gabrielmenezesnog.github.io/">
              @gabrielmenezesnog
            </a>
          </p>
        </div>
      </header>
    </>
  );
};

export default Header;
