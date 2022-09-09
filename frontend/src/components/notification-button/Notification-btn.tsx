import './button.css';
import notificationIcon from '../../assets/img/notification.svg';

function NotificationButton() {
  return (
    <div className="dsmeta-red-btn">
      <img src={notificationIcon} alt="Notificar" />
    </div>
  );
}

export default NotificationButton;
