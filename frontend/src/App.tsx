import Header from './components/header/Header';
import NotificationButton from './components/notification-button/Notification-btn';
import SalesCard from './components/sales-card/sales-card';

function App() {
  return (
    <>
      <Header />
      <main>
        <section id="sales">
          <div className="dsmeta-container"></div>
          <SalesCard />
        </section>
      </main>
    </>
  );
}

export default App;
