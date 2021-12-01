import { c, useProp } from "atomico";
import html from "atomico/html";


const numbers = [1,2,3];

const [num1, num2] = numbers;

function myCounter() {
  const [count, setCount] = useProp("count");
  const [min] = useProp("min");
  const [max] = useProp("max");

  const add = () => {
    if (count < max){
    setCount(count + 1)
    }
  };

  const sub = () => {
    if (count > min){
      setCount(count-1)
    }
  };

  return html`
    <host>
      <button onclick=${sub}>subtract 1</button>
      <span>current count: ${count}</span>
      <button onclick=${add}>add 1</button>
    </host>
  `;
}

myCounter.props = {
  count: {
    type: Number,
    reflect: true,
    value: 0,
  },
  min: {
    type: Number,
    reflect: true,
    value: -1,
  },
  max: {
    type: Number,
    reflect: true,
    value: 1,
  },
};

window.customElements.define("my-counter", c(myCounter));
