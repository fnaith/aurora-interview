import MenuForm from './MenuForm';

function logRequestError(url: string, error: any) {
  const msg: string = `{url=${url},error=${error}}`;
  console.error(msg);
  alert(msg);
}

class AppStore {
  private static readonly DEFAULT_TIMEZONE: string = 'Asia/Taipei';
  private static readonly DEFAULT_LOCALE: string = 'en-US';

  private readonly _apiPath = '/v1';//`${window.location.protocol}//${window.location.hostname}:8080`;

  private _initted: boolean;
  private _updateControlPanel: any;
  private _menuForms: MenuForm[];
  private _menuInfos: any;
  private _userInfos: any;

  constructor() {
    this._initted = false;
    this._menuForms = [];
    this._menuInfos = [];
    this._userInfos = [];
  }

  get updateControlPanel(): any {
    return this._updateControlPanel;
  }

  set updateControlPanel(updateControlPanel: any) {
    this._updateControlPanel = updateControlPanel;
  }

  get menuForms(): MenuForm[] {
    return this._menuForms;
  }

  get menuInfos(): any {
    return this._menuInfos;
  }

  get userInfos(): any {
    return this._userInfos;
  }

  formatTime(timestamp: number): string {
    const date: Date = new Date(timestamp);
    const options: any = { timeZone: AppStore.DEFAULT_TIMEZONE, hour12: false };
    const formattedDateTime: string = date.toLocaleString(AppStore.DEFAULT_LOCALE, options);
    return formattedDateTime;
  }

  init() {
    if (this._initted) {
      return;
    }
    this.readMenus(console.log);
    this.updateControlPanel();
    this._initted = true;
  }

  addMenuForm() {
    var menuForm: MenuForm = new MenuForm();
    menuForm.name = 'Menu Name';
    menuForm.content = 'Menu Content';
    this._menuForms.push(menuForm);
  }

  removeMenuForm(index: number) {
    this._menuForms.splice(index, 1);
  }

  createMenus(callback: any) {
    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(this.menuForms.map(menuForm => {
        return {id: -1, name: menuForm.name, content: menuForm.content,
          createdBy: "", createdAt: 0, updatedBy: '', updatedAt: 0};
      }))
    };
    const url = `${this._apiPath}/menu`;
    fetch(url, requestOptions)
      .then(response => { if (200 != response.status) { throw response.status; } return response.json(); })
      .then(json => {
        this._menuForms = [];
        console.log(json);
        callback(json);
      })
      .catch(error => logRequestError(url, error));
  }

  readMenus(callback: any) {
    const appStore: AppStore = this;
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    };
    const url = `${this._apiPath}/menu`;
    fetch(url, requestOptions)
      .then(response => { if (200 != response.status) { throw response.status; } return response.json(); })
      .then(json => {
        appStore._menuInfos = json;
        console.log(json);
        callback(json);
      })
      .catch(error => logRequestError(url, error));
  }

  readUsers(callback: any) {
    const appStore: AppStore = this;
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    };
    const url = `/user/list`;
    fetch(url, requestOptions)
      .then(response => { if (200 != response.status) { throw response.status; } return response.json(); })
      .then(json => {
        appStore._userInfos = json;
        console.log(json);
        callback(json);
      })
      .catch(error => logRequestError(url, error));
  }

  saveMenuInfo(index: number, callback: any) {
    const appStore: AppStore = this;
    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(this.menuInfos[index])
    };
    const url = `${this._apiPath}/menu/${this.menuInfos[index].id}`;
    fetch(url, requestOptions)
      .then(response => { if (200 != response.status) { throw response.status; } return response.json(); })
      .then(json => {
        appStore.menuInfos[index] = json;
        console.log(json);
        callback(json);
      })
      .catch(error => logRequestError(url, error));
  }

  removeMenuInfo(index: number, callback: any) {
    const appStore: AppStore = this;
    const requestOptions = {
      method: 'DELETE',
      headers: { 'Content-Type': 'application/json' }
    };
    const url = `${this._apiPath}/menu/${this.menuInfos[index].id}`;
    fetch(url, requestOptions)
      .then(response => {
        appStore._menuInfos.splice(index, 1);
        callback();
      })
      .catch(error => logRequestError(url, error));
  }

  getMenuName(menuId: number) {
    for (let i = 0; i < this.menuInfos.length; ++i) {
      const menuInfo: any = this.menuInfos[i];
      if (menuId == menuInfo.id) {
        return `(${menuInfo.id})${menuInfo.name}`;
      }
    }
    return 'Unknown Menu';
  }

  logout() {
    const requestOptions = {
      method: 'GET',
      headers: { 'Content-Type': 'application/json' }
    };
    const url = `/login?logout`;
    window.location.href = url;
  }
}

export default AppStore;
