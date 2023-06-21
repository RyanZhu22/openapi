import {
  getInterfaceInfoByIdUsingGET,
  invokeInterfaceInfoUsingPOST
} from '@/services/openapi-backend/interfaceInfoController';
import { useParams } from '@@/exports';
import { PageContainer } from '@ant-design/pro-components';
import {Button, Card, Descriptions, Divider, Form, Input, message, Spin} from 'antd';
import React, { useEffect, useState } from 'react';

/**
 * 主页
 * @constructor
 */
const Index: React.FC = () => {
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = useState<any>()
  const [invokeLoading, setInvokeLoading] = useState(false);
  const params = useParams();
  console.log(JSON.stringify(params));

  const loadData = async (current = 1, pageSize = 5) => {
    if (!params.id) {
      message.error('The params do not exist');
      return;
    }
    try {
      const res = await getInterfaceInfoByIdUsingGET({
        id: Number(params.id),
      });
      setData(res.data);
      return true;
    } catch (error: any) {
      message.error('下线失败' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData().then(() => {});
  }, []);

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error('The params do not exist');
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPOST({
        id: params.id,
        ...values
      })
      setInvokeRes(res.data);
      message.success("Request Successful");
      return true;
    } catch (error: any) {
      message.error('Request Failed' + error.message);
    }
    setInvokeLoading(false);
  };


  return (
    <PageContainer title="查看接口文档">
      <Card>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="Status">{data.status ? 'Open' : 'Close'}</Descriptions.Item>
            <Descriptions.Item label="Request Url">{data.url}</Descriptions.Item>
            <Descriptions.Item label="Request Method">{data.method}</Descriptions.Item>
            <Descriptions.Item label="Request Params">{data.requestParams}</Descriptions.Item>
            <Descriptions.Item label="Request Header">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="Response Header">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="Craete Time">{data.createTime}</Descriptions.Item>
            <Descriptions.Item label="Update Time">{data.updateTime}</Descriptions.Item>
          </Descriptions>
        ) : (
          <>The interface do not exist</>
        )}
      </Card>
      <Divider />
      <Card>
        <Form
          name="invoke"
          layout="vertical"
          onFinish={onFinish}
        >
          <Form.Item
            label="Request params"
            name="userRequestParams"
          >
            <Input.TextArea />
          </Form.Item>

          <Form.Item wrapperCol={{ span: 16 }}>
            <Button type="primary" htmlType="submit">
              Invoke
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Divider />
      <Card title="Result" loading={!invokeLoading}>
        {invokeRes}
      </Card>
    </PageContainer>
  );
};

export default Index;
