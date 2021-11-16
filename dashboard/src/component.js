import { c, useProp } from "atomico";
import html from "atomico/html";

function myCounter() {
  const [count, setCount] = useProp("count");
  return html`
    <host>
      <button onclick=${() => setCount(count - 1)}>-</button>
      <span>${count}</span>
      <button onclick=${() => setCount(count + 1)}>+</button>
    </host>
  `;
}

myCounter.props = {
  count: {
    type: Number,
    reflect: true,
    value: 0,
  }
};

window.customElements.define("my-counter", c(myCounter));
