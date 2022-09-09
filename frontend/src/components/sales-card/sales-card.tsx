import NotificationButton from '../notification-button/Notification-btn';
import './sales-card.css';

import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import React, { useEffect } from 'react';
import axios from 'axios';
import { BASE_URL } from '../../utils/request';
import { sale } from '../../models/sale';

const SalesCard = () => {
  const max = new Date(),
    min = new Date(new Date().setDate(new Date().getDate() - 365));

  // useState para gerenciar o estado das caixas de datas
  const [minDate, setMinDate] = React.useState(min);
  const [maxDate, setMaxDate] = React.useState(max);

  // useState para gerenciar o estado do objeto 'sale' sale.ts
  // <sale> é o tipo do deste useState e [] indica que é uma lista
  // (lista da requisição das vendas)
  // já o ([]) indica que o valor inicial de 'sales' é vazio
  const [sales, setSales] = React.useState<sale[]>([]);

  React.useEffect(() => {
    // tratamento de data para ficar no formato
    // mm-dd-YYYY e se adequar ao url
    // 0-10 são a quantia de caracteres em mm-dd-YYYY
    // para sempre retornar somente esse pedaço da string minDate
    const dmin = minDate.toISOString().slice(0, 10);
    const dmax = maxDate.toISOString().slice(0, 10);

    const getData = async () => {
      const url = `${BASE_URL}/sales?minDate=${dmin}&maxDate=${dmax}`;
      try {
        const res = await axios.get(url);
        setSales(res.data.content);
      } catch (error) {
        console.log(error);
      }
    };
    getData();
  }, [minDate, maxDate]);

  return (
    <>
      <div className="dsmeta-card">
        <h2 className="dsmeta-sales-title">Vendas</h2>
        <div>
          <div className="dsmeta-form-control-container">
            <DatePicker
              selected={minDate}
              onChange={(date: Date) => {
                setMinDate(date);
              }}
              className="dsmeta-form-control"
              dateFormat="dd/MM/yyyy"
            />
          </div>
          <div className="dsmeta-form-control-container">
            <DatePicker
              selected={maxDate}
              onChange={(date: Date) => {
                setMaxDate(date);
              }}
              className="dsmeta-form-control"
              dateFormat="dd/MM/yyyy"
            />
          </div>
        </div>

        <div>
          <table className="dsmeta-sales-table">
            <thead>
              <tr>
                <th className="show992">ID</th>
                <th className="show576">Data</th>
                <th>Vendedor</th>
                <th className="show992">Visitas</th>
                <th className="show992">Vendas</th>
                <th>Total</th>
                <th>Notificar</th>
              </tr>
            </thead>
            <tbody>
              {sales.map((item) => {
                return (
                  <tr key={item.id}>
                    <td className="show992">{item.id}</td>
                    <td className="show576">
                      {new Date(item.date).toLocaleDateString()}
                    </td>
                    <td>{item.sellerName}</td>
                    <td className="show992">{item.visited}</td>
                    <td className="show992">{item.deals}</td>
                    <td>{item.amount.toFixed(2)}</td>
                    <td>
                      <div className="dsmeta-red-btn-container">
                        <NotificationButton saleId={item.id} />
                      </div>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};

export default SalesCard;
