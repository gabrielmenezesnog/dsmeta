import './button.css';
import notificationIcon from '../../assets/img/notification.svg';
import axios from 'axios';
import { BASE_URL } from '../../utils/request';

// Props com o id respectivo da venda clicada
type Props = {
  saleId: number;
};

function handleClick(saleId: number) {
  const getData = async () => {
    const url = `${BASE_URL}/sales/${saleId}/notification`;
    try {
      const res = await axios.get(url);
      console.log('sucesso');
    } catch (error) {
      console.log(error);
    }
  };
  getData();
}

function NotificationButton({ saleId }: Props) {
  return (
    <div
      className="dsmeta-red-btn"
      onClick={() => {
        handleClick(saleId);
      }}
    >
      <img src={notificationIcon} alt="Notificar" />
    </div>
  );
}

export default NotificationButton;
