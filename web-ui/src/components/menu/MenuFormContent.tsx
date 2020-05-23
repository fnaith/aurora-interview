import React from 'react';
import TextField from '@material-ui/core/TextField';

import global_data from '../../store/global_data';

interface Props {
  index: number;
}

function MenuFormContent(props: Props) {
  const { index } = props;

  const onChange: any = (event: any) => {
    const input: string = event.target.value;

    global_data.menuForms[index].content = input;
  };

  return (
    <TextField id={'menuForm-content-' + index} label="Content"
      defaultValue={global_data.menuForms[index].content} margin="dense" onChange={onChange}/>
  );
}

export default MenuFormContent;
